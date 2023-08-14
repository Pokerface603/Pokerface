import gun from "assets/images/historysummary/gun.svg";
import skeleton from "assets/images/historysummary/skeleton.svg";
import hat from "assets/images/historysummary/hat.svg";

export const getSummaryImg = (imgName) => {
  // eslint-disable-next-line default-case
  switch (imgName) {
    case "win":
      return gun;
    case "lose":
      return skeleton;
    case "winrate":
      return hat;
    default:
      return "error";
  }
};
