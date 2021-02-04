package com.example.pokemonproject.jsonObject;

public class GenListCount {
    private Integer gen1;
    private Integer gen2;
    private Integer gen3;
    private Integer gen4;
    private Integer gen5;
    private Integer gen6;
    private Integer gen7;

    public GenListCount(Integer gen1, Integer gen2, Integer gen3, Integer gen4, Integer gen5, Integer gen6, Integer gen7) {
        this.gen1 = gen1;
        this.gen2 = gen2;
        this.gen3 = gen3;
        this.gen4 = gen4;
        this.gen5 = gen5;
        this.gen6 = gen6;
        this.gen7 = gen7;
    }

    public Integer getGen(int i){
        int g = 0;
        switch (i){
            case 1:
                g = getGen1();
                break;
            case 2:
                g = getGen2();
                break;
            case 3:
                g = getGen3();
                break;
            case 4:
                g = getGen4();
                break;
            case 5:
                g = getGen5();
                break;
            case 6:
                g = getGen6();
                break;
            case 7:
                g = getGen7();
                break;
            default:
                g = getGen7();
        }
        return g;
    }

    public Integer getGen1() {
        return gen1;
    }

    public void setGen1(Integer gen1) {
        this.gen1 = gen1;
    }

    public Integer getGen2() {
        return gen2;
    }

    public void setGen2(Integer gen2) {
        this.gen2 = gen2;
    }

    public Integer getGen3() {
        return gen3;
    }

    public void setGen3(Integer gen3) {
        this.gen3 = gen3;
    }

    public Integer getGen4() {
        return gen4;
    }

    public void setGen4(Integer gen4) {
        this.gen4 = gen4;
    }

    public Integer getGen5() {
        return gen5;
    }

    public void setGen5(Integer gen5) {
        this.gen5 = gen5;
    }

    public Integer getGen6() {
        return gen6;
    }

    public void setGen6(Integer gen6) {
        this.gen6 = gen6;
    }

    public Integer getGen7() {
        return gen7;
    }

    public void setGen7(Integer gen7) {
        this.gen7 = gen7;
    }

}
