import React from "react";
import { getCardImg } from "./divider/getCardImg";

const RoundDivider = ({ roundNum, leftCardNum, rightCardNum }) => {
  return (
    <div className="flex items-center justify-center mt-10">
      <hr className="w-1/4 border-black border-t-2 mx-4" />
      <div className="flex items-center">
        <img
          src={getCardImg(leftCardNum)}
          alt={leftCardNum}
          className="w-20 h-20 mr-2"
        />
        <span
          className="text-black"
          style={{ fontFamily: "BouWestern", fontSize: "30px" }}>
          Round {roundNum}
        </span>
        <img
          src={getCardImg(rightCardNum)}
          alt={rightCardNum}
          className="w-20 h-20 ml-2"
        />
      </div>

      <hr className="w-1/4 border-black border-t-2 mx-4" />
    </div>
  );
};

export default RoundDivider;
