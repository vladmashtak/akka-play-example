package services;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;

@ImplementedBy(CentralMasterImpl.class)
public interface CentralMaster {

    CompletionStage<Object> getSessionTraffic();
}
