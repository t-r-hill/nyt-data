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
public class MostViewedResponse implements Serializable {

    String status;
    String copyright;
    @JsonProperty("num_results")
    Long numResults;
    List<Article> results;

}
