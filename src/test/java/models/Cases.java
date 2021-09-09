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
 //   @Expose
//    int template_id;
//    @Expose
//    int type_id;
//    @Expose
//    int  priority_id;
//    @Expose
//    long estimate;
//    @Expose
//    int milestone_id;
    @Expose
 String refs;


}
