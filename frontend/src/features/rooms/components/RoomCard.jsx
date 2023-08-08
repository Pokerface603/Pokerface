import Button from "@component/Button";
import TierBox from "@component/TierBox";
import React from "react";

function Room() {
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
        <TierBox tier={"J"} />
        <div className="w-full flex flex-col justify-between h-full">
          <div
            className="flex justify-between"
            style={{
              marginRight: "135px",
            }}
          >
            <span>방 이름 </span>
            <span>1 / 2</span>
          </div>

          <div className="flex justify-between">
            <span>방장 이름</span>
            <Button
              label={"입장"}
              background={"var(--orange)"}
              width={"100px"}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default Room;
