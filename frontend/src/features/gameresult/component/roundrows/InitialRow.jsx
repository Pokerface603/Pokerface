import React from "react";
import { getChipImg } from "./chips/getChipImg";

const InitialRow = ({ myRemainChips, opponentRemainChips }) => {
  return (
    <div className="flex justify-center mb-4">
      <div className="w-2/5 flex justify-start">
        <img className="ml-4" src={getChipImg(true)} alt="my" />
        <p style={{ fontFamily: "BouWestern", fontSize: "24px" }}>
          {myRemainChips}
        </p>
      </div>
      <div className="w-1/5 flex  place-content-between">
        <img src={getChipImg(true)} alt="my" />
        <img src={getChipImg(false)} alt="opponent" />
      </div>
      <div className="w-2/5 flex justify-end">
        <p style={{ fontFamily: "BouWestern", fontSize: "24px" }}>
          {opponentRemainChips}
        </p>
        <img className="mr-4" src={getChipImg(false)} alt="opponent" />
      </div>
    </div>
  );
};

export default InitialRow;
