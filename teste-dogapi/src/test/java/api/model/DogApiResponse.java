package api.model;

import java.util.List;
import java.util.Map;

public class DogApiResponse {

    private String status;
    private Object message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    /**
     * Retorna message como Map (para /breeds/list/all)
     */
    @SuppressWarnings("unchecked")
    public Map<String, List<String>> getMessageAsMap() {
        return (Map<String, List<String>>) message;
    }

    /**
     * Retorna message como List (para /breed/{breed}/images)
     */
    @SuppressWarnings("unchecked")
    public List<String> getMessageAsList() {
        return (List<String>) message;
    }

    /**
     * Retorna message como String (para /breeds/image/random)
     */
    public String getMessageAsString() {
        return (String) message;
    }
}