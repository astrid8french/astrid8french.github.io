from pymongo import MongoClient, errors
import pandas as pd


class AnimalShelter:
    """CRUD operations for Animal collection in MongoDB"""

    def __init__(self, user, pwd):
        """Initialize the MongoClient and access the AAC database and animals collection."""
        try:
            self.client = MongoClient(f'mongodb://{user}:{pwd}@nv-desktop-services.apporto.com:31107',
                                      serverSelectionTimeoutMS=5000)
            self.database = self.client['AAC']
            self.collection = self.database['animals']

            # Creating indexes to improve search performance
            self.collection.create_index("name")  # Index on name field
            self.collection.create_index("_id")  # Index on _id field

            print('Welcome to AAC')
        except errors.ServerSelectionTimeoutError:
            raise ConnectionError("Failed to connect to MongoDB. Please check your connection settings.")

    def create(self, data):
        """Create a document in the animals collection."""
        if not isinstance(data, dict):
            raise TypeError("Data must be a dictionary.")

        if not data:
            raise ValueError("Nothing to save, because data parameter is empty.")

        try:
            self.collection.insert_one(data)
            return True
        except errors.PyMongoError as e:
            raise RuntimeError(f"Error inserting document: {e}")

    def read(self, query):
        """Read documents from the animals collection."""
        if not isinstance(query, dict):
            raise TypeError("Query must be a dictionary.")

        if not query:
            raise ValueError("Query parameter is empty.")

        try:
            result = list(self.collection.find(query))
            return result
        except errors.PyMongoError as e:
            raise RuntimeError(f"Error retrieving documents: {e}")

    def update(self, query, update_data, multiple=False):
        """Update documents in the animals collection."""
        if not isinstance(query, dict) or not isinstance(update_data, dict):
            raise TypeError("Query and update_data must be dictionaries.")

        if not query or not update_data:
            raise ValueError("Query or update data parameter is empty.")

        try:
            update_method = self.collection.update_many if multiple else self.collection.update_one
            result = update_method(query, {'$set': update_data})
            modified_count = result.modified_count

            if modified_count > 0:
                print(f"{modified_count} documents updated successfully")
            else:
                print("No documents matched the query.")
            return modified_count
        except errors.PyMongoError as e:
            raise RuntimeError(f"Error updating document(s): {e}")

    def delete(self, query):
        """Delete documents from the animals collection."""
        if not isinstance(query, dict):
            raise TypeError("Query must be a dictionary.")

        if not query:
            raise ValueError("Query parameter is empty.")

        try:
            result = self.collection.delete_many(query)
            deleted_count = result.deleted_count

            if deleted_count > 0:
                print(f"{deleted_count} documents deleted successfully")
            else:
                print("No documents matched the query.")
            return deleted_count
        except errors.PyMongoError as e:
            raise RuntimeError(f"Error deleting document(s): {e}")

    def aggregate(self, pipeline):
        """aggregation pipeline on the animals collection."""
        if not isinstance(pipeline, list):
            raise TypeError("Pipeline must be write in aggregation variables within [].")

        try:
            result = list(self.collection.aggregate(pipeline))
            return result
        except errors.PyMongoError as e:
            raise RuntimeError(f"Error running aggregation pipeline: {e}")
