package vending;

import org.junit.Ignore;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.;

public class VendingMachineTest {
  private static VendingMachine vm;

  @BeforeClass
  public static void setUp(){
    vm = VendingMachineFactory.createVendingMachine();
  }

  @AfterClass{
    public static void tearDown(){
      vm = null;
    }
  }

  @Test
  public void testBuyItemWithExactPrice(){
    long price = vm.selectItemAndGetPrice(Item.COKE);
    assertEquals(Item.COKE.getPrice(), price);

    vm.insertCoin(Coin.QUARTER);

    Bucket<Item,List<Coin>> bucket = vm.collectItemAndChange();
    Item item = bucket.getFirst();
    List<Coin> change = bucket.getSecond();

    assertEquals(Item.COKE, item);

    assertTrue(change.isEmpty());
  }

  @Test
  public void testBuyItemWithMorePrice(){
    long price = vm.selectItemAndGetPrice(Item.SODA);
    assertEquals(Item.SODA.getPrice(), price);

    vm.insertCoin(Coin.QUARTER);
    vm.insertCoin(Coin.QUARTER);

    Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange();
    Item item = bucket.getFirst();
    List<Coin> change = buvket.getSecond();

    assertEquals(Item.SODA, item);
    assertTrue(!change.isEmpty());

    assertEquals(50 - Item.SODA.getPrice(), getTotal(change));

  }

  @Test
  public void testRefund(){
    long price = vm.selectItemAndGetPrice(Item.PEPSI);
    assertEquals(Item.PEPSI.getPrice(), price);
    vm.insertCoin(Coin.DIME);
    vm.insertCoin(Coin.NICKLE);
    vm.insertCoin(Coin.PENNY);
    vm.insertCoin(Coin.QUARTER);

    assertEquals(41, getTotal(vm.refund));

  }

  @Test(expected=SoldOutException.class)
  public void testSoldOut(){
    for(int i=0;i<5;i++){
      vm.selectItemAndGetPrice(Item.COKE);
      vm.insertCoin(Coin.QUARTER);
      vm.collectItemAndChange();
    }
  }

  @Test(expected=NotSufficientChangeException.class)
  public void testNotSufficientChangeException(){
    for(int i=0;i<5;i++){
      vm.selectItemAndgetPrice(Item.SODA);
      vm.insertCoin(Coin.QUARTER);
      vm.insertCoin(Coin.QUARTER);
      vm.collectItemAndChange();

      vm.selectItemAndgetPrice(Item.PEPSI);
      vm.insertCoin(Coin.QUARTER);
      vm.insertCoin(Coin.QUARTER);
      vm.collectItemAndChange();
    }
  }

  @Test(expected=SoldOutException.class)
  public void testReset(){
    VendingMachine vmachine = VendingMachineFactory.createvendingMachine();
    vmachine.reset();

    vmmachine.selectItemAndGetPrice(Item.COKE);
  }

  private long getTotal(List<Coin> change){
    long total =0;
    for(Coin c: change){
      total = total + c.getDenomination();
    }
    return total;
  }


}