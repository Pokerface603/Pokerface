import React from "react";
import { getRewardPercent } from "./getRewardPercent";

const RewardProgressBar = ({
  reward,
  totalMatches,
  background,
  height,
  marginTop,
  style,
}) => {
  const percentage = getRewardPercent(reward);
  return (
    <div className="w-full bg-white" style={{ height, ...style }}>
      <div
        style={{
          width: `${percentage}%`,
          height,
          marginTop,
          background,
        }}></div>
    </div>
  );
};

export default RewardProgressBar;
