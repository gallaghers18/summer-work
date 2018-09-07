import pandas as pd
import json
import matplotlib

pd.set_option('display.expand_frame_repr', False)

json_data = open('ParksData.json').read()
data_dict_list = json.loads(json_data)
raw_data = pd.DataFrame(data_dict_list)
raw_data = raw_data.drop(1)
raw_data = raw_data.drop(52)
raw_data = raw_data.reset_index(drop=True)

park_data = pd.DataFrame()
park_data['Park Name'] = raw_data['title']
park_data['Area sq_km'] = raw_data['area'].apply(lambda x: pd.Series(x['square_km']))
park_data['Area sq_km'] = pd.to_numeric(park_data['Area sq_km'].map(lambda x: x.replace(',', '')))
park_data['State'] = raw_data['states'].apply(lambda x: pd.Series(x[0]['title']))

state_data = pd.read_csv("StateAreaAndPopulation.csv")
state_data['Area sq_km'] = pd.to_numeric(state_data['Area (sqm)'].map(lambda x: x.replace(',', '')))*2.58999
state_data['Population'] = pd.to_numeric(state_data['Population'].map(lambda x: x.replace(',', '')))
state_data = state_data.rename(columns=lambda x: x.strip())

ratio_data = pd.DataFrame(state_data['State'])
ratio_data['Area-Area Ratio'] = pd.Series()
ratio_data['Area-Population Ratio'] = pd.Series()
for state in ratio_data['State']:
    ratio_data.loc[(ratio_data['State'] == state), 'Area-Area Ratio'] = park_data[park_data['State'] == state]['Area sq_km'].sum() / state_data[state_data['State'] == state]['Area sq_km']
    ratio_data.loc[(ratio_data['State'] == state), 'Area-Population Ratio'] = park_data[park_data['State'] == state]['Area sq_km'].sum() / state_data[state_data['State'] == state]['Population']
ratio_data = ratio_data[ratio_data['Area-Area Ratio'] != 0]
ratio_data = ratio_data.sort_values('Area-Area Ratio', ascending=False)
ratio_data = ratio_data.reset_index(drop=True)

area_graph = ratio_data['Area-Area Ratio'].plot(kind='bar', color='yellowgreen', title ="Percentage of State Area Occupied by National Parks", figsize=(13, 9), fontsize=12)
area_graph.set_ylabel("Area Percentage", fontsize=12)
area_graph.set_xticklabels(ratio_data['State'])
