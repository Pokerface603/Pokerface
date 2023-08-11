export const getBetBoxColor = (betType) => {
  switch (betType) {
    case "check":
      return "green";
    case "bet":
      return "blue";
    case "all-in":
      return "red";
    case "call":
      return "green";
    default:
      return "ocher";
  }
};
