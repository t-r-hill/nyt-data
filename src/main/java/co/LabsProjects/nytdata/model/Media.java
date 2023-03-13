package co.LabsProjects.nytdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Media implements Serializable {

    @JsonProperty("type")
    private String type;
    private String subtype;

    @JsonProperty("caption")
    private String caption;
    private String url;

    @JsonProperty("media-metadata")
    private List<Thumbnail> mediaMetadata;

}

