package at.ac.fhsalzburg.swd.spring.dto;

import at.ac.fhsalzburg.swd.spring.model.Service;

public class MusicDTO extends Service {

    private String streamingUrl;

    public MusicDTO(String name, Double preis, Integer serviceId, String streamingUrl) {
        super(name, preis, serviceId);
        this.streamingUrl = streamingUrl;
    }

    public MusicDTO() {

    }

    public String getStreamingUrl() {
        return streamingUrl;
    }
    public void setStreamingUrl(String streamingUrl) {
        this.streamingUrl = streamingUrl;
    }

    @Override
    public String toString() {
        return "Musik{" +
            "streamingUrl='" + streamingUrl + '\'' +
            '}';
    }
}
