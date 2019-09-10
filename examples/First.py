def one():
    one_number = 1
    print(one_number)

def two():
    two_number = 2
    print(two_number)
    
class Elevator:
    
    floor = None
    building = None
    maxPersons = None
    
    def __init__(self, maxPersons, building):
        self.maxPersons = maxPersons
        self.floor = 0
        self.building = building

    def up(self):
        self.floor = self.floor + 1
        
    def down(self):
        self.floor = self.floor - 1  
       
        
class Building:
    
    floorAmount = None
    building = None
    
    def __init__(self, floorAmount):
        self.floorAmount = floorAmount
    
    
    
if __name__ == '__main__':
    building = Building(5)
    elevator = Elevator(10, building)
    elevator.up()
    elevator.up()
    elevator.down()
    print(elevator.floor)