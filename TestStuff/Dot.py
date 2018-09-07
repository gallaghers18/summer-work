from Graphics import *


class Dot:
    def __init__(self, x, y, window):
        self.point = Point(x, y)
        self.window = window
    
    def __str__(self):
        return '({0},{1})'.format(self.getX(), self.getY())
    
    def getX(self):
        return self.point.getX()
    def getY(self):
        return self.point.getY()
    def getDistanceFromEnd(self):
        return 800 - self.getY()

    
    def draw(self):
        self.point.draw(self.window)
        
    def move(self, dx, dy):
        self.point.move(dx, dy)
