//package com.mockneat.random.unit;
//
//import com.mockneat.random.Rand;
//import com.mockneat.types.enums.MarkovChainType;
//
///**
// * Created by andreinicolinciobanu on 02/02/2017.
// */
//public class MarkovsOfTypes implements RandUnitString {
//
//    private static final Integer DEFAULT_MAX_LENGTH = 500;
//
//    private Rand rand;
//    private MarkovsOfTypesMax markovsOfTypesMax;
//
//    public MarkovsOfTypes(Rand rand, MarkovChainType... types) {
//        this.rand = rand;
//        this.markovsOfTypesMax = new MarkovsOfTypesMax(rand, DEFAULT_MAX_LENGTH, types);
//    }
//
//    @Override
//    public String val() {
//        return markovsOfTypesMax.val();
//    }
//
//    @Override
//    public Rand getRand() {
//        return this.rand;
//    }
//}
