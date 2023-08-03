import TierBoxSvg from "../utils/TierBoxSvg";

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