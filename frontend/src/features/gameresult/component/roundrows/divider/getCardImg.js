import redCard1 from "@asset/images/pokercards/cardHeartsA.png";
import redCard2 from "@asset/images/pokercards/cardHearts2.png";
import redCard3 from "@asset/images/pokercards/cardHearts3.png";
import redCard4 from "@asset/images/pokercards/cardHearts4.png";
import redCard5 from "@asset/images/pokercards/cardHearts5.png";
import redCard6 from "@asset/images/pokercards/cardHearts6.png";
import redCard7 from "@asset/images/pokercards/cardHearts7.png";
import redCard8 from "@asset/images/pokercards/cardHearts8.png";
import redCard9 from "@asset/images/pokercards/cardHearts9.png";
import redCard10 from "@asset/images/pokercards/cardHearts10.png";
import blackCard1 from "@asset/images/pokercards/cardSpadesA.png";
import blackCard2 from "@asset/images/pokercards/cardSpades2.png";
import blackCard3 from "@asset/images/pokercards/cardSpades3.png";
import blackCard4 from "@asset/images/pokercards/cardSpades4.png";
import blackCard5 from "@asset/images/pokercards/cardSpades5.png";
import blackCard6 from "@asset/images/pokercards/cardSpades6.png";
import blackCard7 from "@asset/images/pokercards/cardSpades7.png";
import blackCard8 from "@asset/images/pokercards/cardSpades8.png";
import blackCard9 from "@asset/images/pokercards/cardSpades9.png";
import blackCard10 from "@asset/images/pokercards/cardSpades10.png";

export const getCardImg = (cardNum) => {
  switch (cardNum) {
    case 19:
      return blackCard10;
    case 18:
      return blackCard9;
    case 17:
      return blackCard8;
    case 16:
      return blackCard7;
    case 15:
      return blackCard6;
    case 14:
      return blackCard5;
    case 13:
      return blackCard4;
    case 12:
      return blackCard3;
    case 11:
      return blackCard2;
    case 10:
      return blackCard1;
    case 9:
      return redCard10;
    case 8:
      return redCard9;
    case 7:
      return redCard8;
    case 6:
      return redCard7;
    case 5:
      return redCard6;
    case 4:
      return redCard5;
    case 3:
      return redCard4;
    case 2:
      return redCard3;
    case 1:
      return redCard2;
    default:
      return redCard1;
  }
};
