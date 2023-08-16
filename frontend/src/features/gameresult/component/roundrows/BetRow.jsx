import React from "react";
import { getChipImg } from "./chips/getChipImg";
import BetTypeBox from "./BetTypeBox";

const BetRow = ({ isMyBet, bettingMoney, betType }) => {
  return (
    <div className="flex mb-4">
      <div className="w-2/5">
        {isMyBet && (
          <div className="flex justify-between">
            <BetTypeBox betType={betType} />
            <div className="flex">
              <img src={getChipImg(true)} alt="chips" />
              <p style={{ fontFamily: "BouWestern", fontSize: "24px" }}>
                {bettingMoney}
              </p>
            </div>
          </div>
        )}
      </div>
      <div className="w-1/5 flex justify-center">
        <p style={{ fontFamily: "BouWestern", fontSize: "24px" }}>
          {isMyBet ? ">" : "<"}
        </p>
      </div>
      <div className="w-2/5">
        {!isMyBet && (
          <div className="flex content-between justify-between">
            <div className="flex">
              <p style={{ fontFamily: "BouWestern", fontSize: "24px" }}>
                {bettingMoney}
              </p>
              <img src={getChipImg(true)} alt="chips" />
            </div>
            <BetTypeBox betType={betType} />
          </div>
        )}
      </div>
    </div>
  );
};

export default BetRow;
