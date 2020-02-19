package com.scut.indoorLocation.utility;

import com.scut.indoorLocation.entity.FingerPrint2D;
import com.scut.indoorLocation.exception.FingerPrintEmptyException;
import com.scut.knn_algorithm.KnnAlgorithm;
import com.scut.point.IFingerPrint;
import com.scut.point.Vector2D;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mingor on 2020/2/17 19:35
 */
@Component
@Slf4j
public class LocationAlgorithmUtil {

    public Vector2D calculatePosition2D(List<FingerPrint2D> fingerPrintsHistory, IFingerPrint fingerPrint) throws FingerPrintEmptyException {
        if(fingerPrint != null){
            return KnnAlgorithm.knnAlgorithm(fingerPrintsHistory, fingerPrint, 3);
        }else {
            throw new FingerPrintEmptyException("没有指纹库信息");
        }

    }


}
