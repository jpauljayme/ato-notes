package dev.mayhm.atonotes.model;

public record Note(
        int id,
        String title,
        String body
){

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
