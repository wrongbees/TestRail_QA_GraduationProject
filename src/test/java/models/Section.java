package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Section {
    @Expose
    int id;
    @Expose
    int suit_id;
    @Expose
    String name;
    @Expose
    String description;
    @Expose
    int display_order;
    @Expose
    int depth;

}
