package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cases {
    @Expose
    String title;
    @Expose
    int id;
    @Expose
    String refs;


}
