package sprint3_product;

public class Cell {
	private Integer sym;
    private String clr;

    public Cell()
    {
        this.sym = 0;
        this.clr = "None";
    }
    public void setMyPair(Integer aSym, String aClr)
    {
        this.sym = aSym;
        this.clr = aClr;
    }
    public Integer getSym()   { return sym; }
    public String getClr() { return clr; }

}
