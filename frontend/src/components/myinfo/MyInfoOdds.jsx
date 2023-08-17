import React from "react";

const MyInfoOdds = ({ wins, totalMatches }) => {
  const losses = totalMatches - wins;
  const winPercentage = Math.round((wins / totalMatches) * 100);

  return (
    <div className="my-0 mx-3 gap-2 h-9 flex items-center">
      <span
        style={{
          fontFamily: "Unchained-Spaghetti",
          color: "gray",
          fontSize: "30px",
        }}
      >
        ODDS
      </span>
      {!isNaN(losses) && (
        <span
          style={{
            fontFamily: "Unchained-Spaghetti",
            fontSize: "20px",
          }}
        >
          {wins} WINS {losses} LOSSES ({winPercentage}%)
        </span>
      )}
    </div>
  );
};

export default MyInfoOdds;
