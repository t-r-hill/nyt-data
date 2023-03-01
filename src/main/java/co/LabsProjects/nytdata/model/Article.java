package co.LabsProjects.nytdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private Long id;

    private String url;
    private String section;
    private String subsection;
    private String byline;
    private String title;
    private String imageUrl;

    @JsonProperty("abstract")
    private String summary;

    private List<Media> media;

    public void setImageUrl(){
        this.imageUrl = media.get(0).getMediaMetadata().get(0).getUrl();
    }

    public boolean containsMedia(){
        return !(media == null || media.isEmpty());
    }
}
