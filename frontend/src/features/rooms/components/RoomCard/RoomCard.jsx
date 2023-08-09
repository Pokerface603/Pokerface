import Button from "@component/Button";
import TierBox from "@component/TierBox";
import React from "react";
import Dividor from "./Dividor";

function RoomCard({ title, hostName, hostTier, playerCount }) {
  console.log(title, hostName, hostTier, playerCount);
  return (
    <div
      style={{
        border: "6px solid #CCC0BB",
        borderRadius: "18px",
      }}
    >
      <div
        style={{
          width: "580px",
          height: "130px",
          padding: "12px 15px",
          gap: "3px",
        }}
        className="bg-room box-border bg-cover flex items-center "
      >
        <TierBox tier={hostTier} />
        <div
          className="w-full flex flex-col justify-evenly h-full font-nexonGothic"
          style={{
            fontSize: "25px",
            fontWeight: "bold",
          }}
        >
          <div
            className="flex justify-between items-center"
            style={{
              marginRight: "135px",
            }}
          >
            <span>{title}</span>
            <span
              style={{
                fontFamily: "Unchained-Spaghetti",
                fontSize: "30px",
              }}
            >
              {playerCount} / 2
            </span>
          </div>
          <Dividor />
          <div className="flex justify-between items-center">
            <span>{hostName}</span>
            <Button
              label={"입장"}
              background={"var(--orange)"}
              width={"100px"}
              color={"white"}
              height={"33px"}
              fontSize={"20px"}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default RoomCard;
