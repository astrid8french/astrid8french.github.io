"""Astrid French"""
from pymongo import MongoClient

class AnimalShelter:
    """CRUD operations for Animal collection in MongoDB"""

    def __init__(self, user, pwd):
        """Initialize the MongoClient and access the AAC database and animals collection."""
        self.client = MongoClient(f'mongodb://{user}:{pwd}@nv-desktop-services.apporto.com:31107')
        self.database = self.client['AAC']
        self.collection = self.database['animals']
        print('Welcome to AAC')

    def create(self, data):
        """Create a document in the animals collection."""
        if data:
            self.collection.insert_one(data)
            return True
        else:
            raise ValueError("Nothing to save, because data parameter is empty")

    def read(self, query=None):
        """Read documents from the animals collection."""
        if query is None:
            query = {}  
        result = list(self.collection.find(query))
        return result
            
    def update(self, query, update_data, multiple=False):
        """Update documentsfrom the animals collection."""
    
        if not query or not update_data:
            raise ValueError("Query or update data parameter is empty")
    
        update_method = self.collection.update_many if multiple else self.collection.update_one
        result = update_method(query, {'$set': update_data})
        if result.modified_count > 0:
            print("Update successful")
    
        return result.modified_count

    def delete(self, query):
        """Delete documents from the animals collection."""
       
        if not query:
            raise ValueError("Query parameter is empty")
        
        result = self.collection.delete_many(query)
        if result.deleted_count > 0:
            print("Delete successful")
        
        return result.deleted_count