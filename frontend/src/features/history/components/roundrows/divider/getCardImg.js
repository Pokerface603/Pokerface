import card1 from "@asset/images/pokercards/cardHeartsA.svg";
import card2 from "@asset/images/pokercards/cardHearts2.svg";
import card3 from "@asset/images/pokercards/cardHearts3.svg";
import card4 from "@asset/images/pokercards/cardHearts4.svg";
import card5 from "@asset/images/pokercards/cardHearts5.svg";
import card6 from "@asset/images/pokercards/cardHearts6.svg";
import card7 from "@asset/images/pokercards/cardHearts7.svg";
import card8 from "@asset/images/pokercards/cardHearts8.svg";
import card9 from "@asset/images/pokercards/cardHearts9.svg";
import card10 from "@asset/images/pokercards/cardHearts10.svg";

export const getCardImg = (cardNum) => {
  switch (cardNum) {
    case 10:
      return card10;
    case 9:
      return card9;
    case 8:
      return card8;
    case 7:
      return card7;
    case 6:
      return card6;
    case 5:
      return card5;
    case 4:
      return card4;
    case 3:
      return card3;
    case 2:
      return card2;
    default:
      return card1;
  }
};
