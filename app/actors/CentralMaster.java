package actors;

import com.google.inject.ImplementedBy;

@ImplementedBy(CentralMasterImpl.class)
public interface CentralMaster {

    void getData();
}
