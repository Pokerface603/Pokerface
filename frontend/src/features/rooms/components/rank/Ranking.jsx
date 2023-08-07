import Frame from "../../../../assets/images/rooms/frame.svg";
import Saloon from "../../../../assets/images/rooms/saloon.svg";
import TierNoBox from "../TierNoBox";

const Ranking = ({ id, nickname, tier, reward }) => {
  const isenglish = /^[A-Za-z]+$/;

  const fontFamily = isenglish.test(nickname)
    ? "Unchained-Spaghetti"
    : "NexonGothic";

  return (
    <div className="relative w-300 h-200">
      <div className="flex items-center justify-center w-full h-full">
        <img src={Frame} alt="frame" className="absolute z-0 object-cover" />
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

      <div className="flex items-center justify-center">
        <div
          className="grid grid-cols-8 gap-2 items-center"
          style={{ width: "380px", height: "43px" }}
        >
          <p
            className="inline-block text-center"
            style={{ fontFamily, fontSize: "30px" }}
          >
            #1
          </p>
          <div className="m-auto">
            <TierNoBox tier={tier} />
          </div>
          <p className="col-span-4" style={{ fontFamily, fontSize: "30px" }}>
            {nickname}
          </p>
          <p className="col-span-2" style={{ fontFamily, fontSize: "30px" }}>
            ${reward}
          </p>
        </div>
      </div>

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
