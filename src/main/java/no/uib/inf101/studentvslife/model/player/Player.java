package no.uib.inf101.studentvslife.model.player;

public class Player implements IPlayer{

  private int money;
  private int lvl;
  private int konteAttempts;

  
  public Player() {
    this.lvl = 0;
    this.konteAttempts = 3;
    this.money = updateMoney();
  }

  @Override
  public void gameover() {
    if (this.konteAttempts == 0) {
      this.konteAttempts = 3;
      this.lvl = 0;
    } else {
      this.konteAttempts -= 1;
    }
    this.money = updateMoney();
  }

  @Override
  public int getKonteAttempts() {
    return this.konteAttempts;
  }

  @Override
  public boolean money(int price) {
    if (this.money + price >= 0) {
      this.money += price;
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int getMoney() {
    return this.money;
  }

  @Override
  public int getLvl() {
    return this.lvl;
  }

  @Override
  public void lvlUp() {
    this.lvl += 1;
    this.money = updateMoney();

  }

  @Override
  public int getMaxAttackers() {
    switch (this.lvl) {
    case 0:
      return 3;
    case 1:
      return 3;
    default:
      return 5;

    }
  }

  @Override
  public int getAttackersInLvl() {
    switch (this.lvl) {
    case 0:
      return 1;
    case 1:
      return 3;
    case 2:
      return 5;
    case 3:
      return 5;
    default:
      return 1000;
    }
  }


  private int updateMoney() {
    switch (this.lvl) {
    case 0:
      return 200;
    case 1:
      return 400;
    case 2:
      return 500;
    case 3:
      return 600;
    default:
      return 600;

    }
  }

}
