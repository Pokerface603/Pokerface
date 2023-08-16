import myChip from "@asset/images/chips/mychip.svg";
import opponentChip from "@asset/images/chips/opponentchip.svg";

export const getChipImg = (isMyChip) => {
  switch (isMyChip) {
    case true:
      return myChip;
    default:
      return opponentChip;
  }
};
