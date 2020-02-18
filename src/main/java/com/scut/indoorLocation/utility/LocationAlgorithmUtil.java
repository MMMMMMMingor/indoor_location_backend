package com.scut.indoorLocation.utility;

import com.scut.knn_algorithm.KnnAlgorithm;
import com.scut.point.IFingerPrint;
import com.scut.point.Vector2D;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mingor on 2020/2/17 19:35
 */
@Component
@Scope("prototype")
@Slf4j
public class LocationAlgorithmUtil {

    private List<IFingerPrint> fingerPrints;

    public LocationAlgorithmUtil(List<IFingerPrint> fingerPrints){
        this.fingerPrints = fingerPrints;
    }

    public Vector2D calculatePosition2D(IFingerPrint fingerPrint){

        return KnnAlgorithm.knnAlgorithm(fingerPrints, fingerPrint, 3);

    }

}
