package scot.jalba;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class Topics {

    private final List<Topic> topics = new ArrayList<>();

    public Topics(List<Map<String, Object>> topicsDto) {
        topicsDto.forEach(t -> topics.add(
                new Topic(
                        (long) t.get("id"),
                        (String) t.get("title"),
                        (String) t.get("text"),
                        (String) t.get("author")
                )));
    }

    public void addOrUpdate(Topic t) {
        if (topics.contains(t)) {
            Topic existingTopic = topics.get(topics.indexOf(t));
            existingTopic.updateFrom(t);
        } else {
            topics.add(t);
        }
    }

    public void fill(List<Map<String, Object>> topicsDto) {
        topicsDto.clear();
        topics.forEach(t -> topicsDto.add(t.asMap()));
    }

    public Collection<? extends Map<String, Object>> get() {
        List<Map<String, Object>> result = new ArrayList<>();
        topics.forEach(t -> result.add(t.asMap()));
        return result;

    }
}
