import React from "react";
import Saloon from "../../../../assets/images/rooms/saloon.svg";
import Frame from "../Frame";
import RankingItem from "./RankingItem";

const Ranking = ({ rankers }) => {
  return (
    <div className="relative w-300 h-200">
      <div className="flex items-center justify-center w-full h-full">
        <Frame
          style={{ position: "absolute", zIndex: 0, objectFit: "cover" }}
        />
        <p
          style={{
            fontFamily: "Unchained-Spaghetti",
            fontSize: "40px",
            marginBottom: "7px",
          }}
        >
          TOP 5
        </p>
      </div>

      {rankers.map(({ nickname, tier, rating }, ranking) => {
        return (
          <RankingItem
            nickname={nickname}
            tier={tier}
            reward={rating}
            ranking={ranking + 1}
          />
        );
      })}

      <img
        src={Saloon}
        style={{
          top: "70px",
          right: "110px",
          position: "absolute",
          zIndex: 1,
        }}
        alt="saloon"
      />
    </div>
  );
};

export default Ranking;
