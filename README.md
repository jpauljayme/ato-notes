# Ato Notes
The project, Ato Notes, serves as a CRUD-based Spring Boot application, providing a simple service to create and manage your own notes.

## Setup
1. Download the project repository.
2. Open the project via an IDE of your preference. Mine is Intellij Community as it is free to use.
3. In the application.properities file, which you can find in the resource folder, you may set your preferred port. The default port is 8085.
4. Start the application.
5. You can use any web testing app. I use Postman.

## Usage
A note object has the following fields :
1. Id - unique identifier of a note. Auto-generated. Acceptable values are non-negative integers. i.e 1, 2, 3...
2. Title - title of a note. String value. **Mandatory**, does not accept an empty value. Returns an error if empty.
3. Body - body text of a note. String value. **Mandatory**, does not accepty empty value. Returns an error if empty.


Here are the endpoints that you can access.

```bash
1. localhost:8085/notes : Create a new note.

Sample request body.

{
    "title": "First entry",
    "body": "You have to start somewhere, right? Committing your first step is the hardest yet the pivot that you need to be fulfilled."
}
```

```
2. localhost:8085/notes : Retrieve all notes.

Sample response body.

{
    "title": "First entry",
    "body": "You have to start somewhere, right? Committing your first step is the hardest yet the pivot that you need to be fulfilled."
}
```

```
3. localhost:8085/notes{id} : Retrieve a specific note by ID.
Sample url : localhost:8085/notes/1

Sample response body.

{
    "title": "First entry",
    "body": "You have to start somewhere, right? Committing your first step is the hardest yet the pivot that you need to be fulfilled."
}
```

```
4. localhost:8085/notes/{id }: Update a specific note.
Sample url : localhost:8085/notes/1
Sample request body.

{
    "title": "Updating my first entry",
    "body": "Yes, it's the right time to maneuver the road towards a healthier lifestyle. Get some rest so you can enjoy the sojourn."
}
```

```
5. localhost:8085/notes/{id }: Delete a specific note by ID.
Sample url : localhost:8085/notes/1
Sample response : Deleted note with id 1
```
