package co.LabsProjects.nytdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doc implements Serializable  {

    @JsonProperty("abstract")
    String abs;
    @JsonProperty("web_url")
    String webUrl;
    String snippet;
    @JsonProperty("lead_paragraph")
    String leadParagraph;
    String source;
    @JsonProperty("pub_date")
    String pubDate;
    @JsonProperty("document_type")
    String documentType;
    String imageUrl;

    Byline byline;
    Headline headline;

    List<Multimedia> multimedia;

    public void setImageUrl(){
        String url = multimedia.stream()
                .filter(x -> x.getSubType().equals("largeHorizontal375"))
                .findFirst()
                .map(Multimedia::getUrl)
                .orElse("");
        this.imageUrl = "https://www.nytimes.com/" + url;
    }
}
