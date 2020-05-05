package scot.jalba;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Topic {

    private final long id;
    private final String title;
    private String description;
    private final String author;

    public Topic(long id, String title, String description, String author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public void updateFrom(Topic topic) {
        this.description = topic.description;
    }

    public Map<String, Object> asMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("title", title);
        result.put("text", description + " : " + id);
        result.put("author", author);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Topic topic = (Topic) o;
        return Objects.equals(title, topic.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
