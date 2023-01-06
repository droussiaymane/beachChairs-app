package at.ac.fhsalzburg.swd.spring.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MUSIK")
public class Music extends Service{

    private String streamingUrl;

    public Music(String name, Double preis, Integer serviceId, String streamingUrl) {
        super(name, preis, serviceId);
        this.streamingUrl = streamingUrl;
    }

    public Music() {

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
