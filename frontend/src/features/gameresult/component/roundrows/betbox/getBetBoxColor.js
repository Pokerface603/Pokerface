export const getBetBoxColor = (betType) => {
  switch (betType) {
    case "CHECK":
      return "green";
    case "BET":
      return "blue";
    case "ALL-IN":
      return "red";
    case "CALL":
      return "green";
    default:
      return "black";
  }
};
