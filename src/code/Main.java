package code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        InBean ib = new InBean();
        ib.setTorihikisakiName("セントラル商会");
        ib.setTorihikiDate( LocalDate.of(2018, 5, 2) );
        ib.setGoukei(400);

        ib.getLines().add(  new InLineBean("消しゴム",1,100) );
        ib.getLines().add(  new InLineBean("鉛筆",20,300) );

        OutBean ob = tenki( ib );
        System.out.println( ob.toString() );
    }

    @Override
    public String toString() {
        return "Main []";
    }

    static OutBean tenki( InBean ib)
    {
        OutBean ob = new OutBean();
//    	1．日付を書き写す
//    	 1.1.元伝票の日付を確認
        LocalDate torihikidate = ib.getTorihikiDate();
//    	 1.2.転記先に日付を記入

        ob.setTorihikiDate(torihikidate);
//    	2．取引先を書き写す
//    	 2.1.元伝票の取引先を確認
        String torihikisaki = ib.getTorihikisakiName();
//    	 2.2.転記先に取引先を記入
        ob.setTorihikisakiName(torihikisaki);
//    	3．元伝票の取引数を確認
        int torihikisu = ib.getLines().size();
//    	4．3で確認した数にあわせて表を作成(1ページにつき取引4行分まで)
//    	 4.1.元伝票の取引数÷4+1の計算を行う(余り無視)
        //int pagenum = Math.floorDiv(torihikisu, 4);
//    	 4.2.転記先に5×4の表を作成

//    	 4.3.4.2の作業を(4.1の計算結果)枚のページ数分繰り返す

//    	 4.4.元伝票の表1行目に記入されている各列の名称を確認

//    	 4.5.転記先に作成した表に4.1.で確認した列名を記入

//    	 4.6．4.4と4.5の作業を表全てに対して適用するまで繰り返し行う

//    	5．4で作成した表に元伝票の取引情報を1行ずつ記入
//    	 5.1.元伝票の取引の番号を確認
        for(int i=0; i<torihikisu; i++)
        {
//    	 5.2.転記先に取引の番号を記入
            OutLineBean olb = new OutLineBean();
//    	 5.3.元伝票の商品名を確認
            String shouhinmei = ib.getLines().get(i).getShouhinName();
//    	 5.4.転記先に商品名を記入
            olb.setShouhinName(shouhinmei);
//    	 5.5.元伝票の数量を確認
            int suuryo = ib.getLines().get(i).getSuuryo();
//    	 5.6.転記先に数量を記入
            olb.setSuuryo(suuryo);
//    	 5.7.元伝票の金額を確認
            int kingaku = ib.getLines().get(i).getKingaku();
//    	 5.8.転記先に金額を記入
            olb.setKingaku(kingaku);
//    	 5.9.元伝票のデータがなくなるまで5.1から5.8の作業を繰り返す
            ob.getLines().add(olb);
        }
//    	6．合計金額を確認
        int goukei = ib.getGoukei();
        int newgoukei = 0;
        for(int i=0; i<torihikisu; i++)
        {
            newgoukei += ob.getLines().get(i).getKingaku();
        }
//    	7．合計金額を記入
        if(goukei == newgoukei)
        {
            ob.setGoukei(newgoukei);
        }

        return ob;
    }

}
//------------------------------------
class InBean
{
    private LocalDate 			torihikiDate;
    private String	  			torihikisakiName;
    private int		  			goukei;
    private List<InLineBean> 	lines= new ArrayList<InLineBean>();

    public LocalDate getTorihikiDate() {
        return torihikiDate;
    }
    public void setTorihikiDate(LocalDate torihikiDate) {
        this.torihikiDate = torihikiDate;
    }
    public String getTorihikisakiName() {
        return torihikisakiName;
    }
    public void setTorihikisakiName(String torihikisakiName) {
        this.torihikisakiName = torihikisakiName;
    }
    public int getGoukei() {
        return goukei;
    }
    public void setGoukei(int goukei) {
        this.goukei = goukei;
    }
    public List<InLineBean> getLines() {
        return lines;
    }



}
class InLineBean
{
    private String shouhinName;
    private int		suuryo;
    private int		kingaku;



    public InLineBean(String shouhinName, int suuryo, int kingaku) {
        super();
        this.shouhinName = shouhinName;
        this.suuryo = suuryo;
        this.kingaku = kingaku;
    }
    public String getShouhinName() {
        return shouhinName;
    }
    public void setShouhinName(String shouhinName) {
        this.shouhinName = shouhinName;
    }
    public int getSuuryo() {
        return suuryo;
    }
    public void setSuuryo(int suuryo) {
        this.suuryo = suuryo;
    }
    public int getKingaku() {
        return kingaku;
    }
    public void setKingaku(int kingaku) {
        this.kingaku = kingaku;
    }


}
//------------------------------------
class OutBean
{
  private LocalDate 			torihikiDate;
  private String	  			torihikisakiName;
  private int		  			goukei;
  private List<OutLineBean> 	lines= new ArrayList<OutLineBean>();

    public LocalDate getTorihikiDate() {
        return torihikiDate;
    }
    public void setTorihikiDate(LocalDate torihikiDate) {
        this.torihikiDate = torihikiDate;
    }
    public String getTorihikisakiName() {
        return torihikisakiName;
    }
    public void setTorihikisakiName(String torihikisakiName) {
        this.torihikisakiName = torihikisakiName;
    }
    public int getGoukei() {
        return goukei;
    }
    public void setGoukei(int goukei) {
        this.goukei = goukei;
    }
    public List<OutLineBean> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return "OutBean [torihikiDate=" + torihikiDate + ", torihikisakiName=" + torihikisakiName + ", goukei=" + goukei
                + ", lines=" + lines + "]";
    }

}
//------------------------------------
class OutLineBean
{
  private String shouhinName;
  private int		suuryo;
  private int		kingaku;
    public String getShouhinName() {
        return shouhinName;
    }
    public void setShouhinName(String shouhinName) {
        this.shouhinName = shouhinName;
    }
    public int getSuuryo() {
        return suuryo;
    }
    public void setSuuryo(int suuryo) {
        this.suuryo = suuryo;
    }
    public int getKingaku() {
        return kingaku;
    }
    public void setKingaku(int kingaku) {
        this.kingaku = kingaku;
    }

    @Override
    public String toString() {
        return "OutLineBean [shouhinName=" + shouhinName + ", suuryo=" + suuryo + ", kingaku=" + kingaku + "]";
    }


}