import TierBoxSvg from "./constants/TierBoxSvg";

const TierBox = ({ tier, width, height }) => {
  return (
    <img
      src={TierBoxSvg[tier]}
      alt={tier}
      width={width}
      height={height}
    />
  );
};

export default TierBox;