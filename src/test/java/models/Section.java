package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Section {
    @Expose
    String name;
    @Expose
    int depth;
    @Expose
    String description;
    @Expose
    int display_order;
    @Expose
    int id;
//    @Expose
//    int parent_id;

}
