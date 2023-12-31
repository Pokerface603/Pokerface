import React, { useContext, useEffect, useState } from "react";
import Parchment from "@component/Parchment";
import WoodBackground from "@component/WoodBackground";
import Button from "@component/Button";
import MyInfo from "@component/myinfo/MyInfo";
import StartButton from "./StartButton";
import Logo from "./Logo";
import SearchBar from "./SearchBar";
import Ranking from "./rank/Ranking";
import ConnectList from "./connect/ConnectList";
import Tab from "./Tab/Tab";
import RoomMakeModal from "./RoomMakeModal/RoomMakeModal";
import { getRooms, quickStart, searchRoomsWithKeyword } from "../api/room";
import { getRankers } from "../api/ranker";
import { useNavigate } from "react-router-dom";
import Navigator from "./Paging/Navigator";
import { useSelector } from "react-redux";
import { hashOpenviduTitle } from "@util/hashing";
import { participateRoom } from "../api/session";
import { WebSocketContext } from "context/WebsocketProvider";
import useRatingInfo from "@hook/useRatingInfo";
import soundEffects from "@config/soundEffects";
import EntryModal from "@component/EntryModal";
import Loading from "@component/Loading";
import RoomCard from "./RoomCard/RoomCard";

const RoomsPage = () => {
  const { email, nickname } = useSelector((state) => state.user);
  const { userRatingInfo } = useRatingInfo(email);
  const [showRoomMakeModal, setShowRoomMakeModal] = useState(false);
  const [rooms, setRooms] = useState([]);
  const [mode, setMode] = useState("NORMAL");
  const [searchKeyword, setSearchKeyword] = useState("");
  const [rankers, setRankers] = useState([]);
  const [pageInfo, setpageInfo] = useState({
    totalPageCount: 0,
    curPage: 1,
  });
  const [isLoading, setIsLoading] = useState(false);

  const navigate = useNavigate();

  const onClickTab = (selectedMode) => {
    soundEffects.load.play();
    setMode(selectedMode);

    if (mode === selectedMode) {
      fetchRoomData(1);
    }
  };

  const onClickMakeRoom = () => {
    soundEffects.shot.play();
    setShowRoomMakeModal(true);
  };

  const closeModal = () => {
    soundEffects.load.play();
    setShowRoomMakeModal(false);
  };

  const onClickTutorial = () => {
    soundEffects.load.play();
    navigate("../tutorial");
  };

  const onInputSearchKeyword = (e) => {
    const {
      target: { value },
    } = e;

    setSearchKeyword(value);
  };

  const onQuickStart = async () => {
    setIsLoading(true);
    soundEffects.shot.play();
    try {
      const { title, gameMode } = await quickStart(email);
      const sessionId = hashOpenviduTitle(title);

      if (gameMode !== "BLIND") {
        const token = await participateRoom(sessionId, "");
        navigate(`../game/${sessionId}`, { state: { token, gameMode } });
      } else {
        navigate(`../game/${sessionId}`, { state: { gameMode } });
      }
    } catch (error) {
      alert("현재 입장가능한 방이 없습니다.");
    } finally {
      setIsLoading(false);
    }
  };

  const navigatePage = (pageNumber) => {
    fetchRoomData(pageNumber);
  };

  const onClickMyPage = () => {
    soundEffects.load.play();
    navigate("../mypage");
  };

  async function fetchRoomData(pageNumber) {
    const { totalPageCount, roomInfoResList: rooms } = await getRooms(
      pageNumber,
      mode
    );

    setRooms(rooms);
    setpageInfo((prevPageInfo) => ({
      totalPageCount,
      curPage: pageNumber,
    }));
  }

  async function searchRooms() {
    soundEffects.load.play();
    const { totalPageCount, roomInfoResList: rooms } =
      await searchRoomsWithKeyword(mode, searchKeyword);
    setSearchKeyword("");
    setRooms(rooms);
    setpageInfo((prevPageInfo) => ({
      totalPageCount,
      curPage: 1,
    }));
  }

  async function fetchRankers() {
    const curRankers = await getRankers();

    setRankers((prevRankers) => curRankers);
  }

  useEffect(() => {
    fetchRoomData(1);
  }, [mode]);

  useEffect(() => {
    // Todo: API 배포 후 주석 풀 예정
    fetchRankers();
  }, []);

  return (
    <>
      <EntryModal />
      <WoodBackground>
        <div className="grid grid-flow-col gap-4">
          <div className="col-span-1">
            <div className style={{ width: "1280px", height: "870px" }}>
              <div className="h-1/4">
                <div className="grid grid-cols-3 gap-10 h-full">
                  <div className="col-span-2 grid mb-3">
                    <Logo />
                    <div style={{ width: "660px" }}>
                      <SearchBar
                        onClickSearch={searchRooms}
                        onInputSearchKeyword={onInputSearchKeyword}
                        searchKeyword={searchKeyword}
                      />
                    </div>
                  </div>
                  <div>
                    <StartButton
                      onClickCreateRoom={onClickMakeRoom}
                      onQuickStart={onQuickStart}
                      onClickTutorial={onClickTutorial}
                    />
                  </div>
                </div>
              </div>

              <Parchment
                style={{ width: "1280px", height: "652px", display: "flex" }}
              >
                <div className="flex flex-col justify-center items-center mx-auto">
                  <div
                    style={{
                      width: "1225px",
                      height: "105px",
                      background: "var(--ocher)",
                    }}
                  >
                    <Tab onClickTab={onClickTab} mode={mode} />
                  </div>

                  {/* 방 정보들이 들어가야하는 부분 */}
                  <div
                    style={{
                      width: "1225px",
                      height: "520px",
                      background: "var(--ocher)",
                      alignContent: "start",
                      padding: "15px",
                    }}
                    className="justify-between flex flex-col"
                  >
                    <div
                      className="w-full flex flex-wrap content-start"
                      style={{
                        gap: "10px",
                        flex: "1 0 auto",
                      }}
                    >
                      {rooms.map((room) => (
                        <RoomCard
                          id={room.title}
                          {...room}
                          fetchRoomData={fetchRoomData}
                        />
                      ))}
                    </div>

                    <div className="flex justify-center w-full">
                      <Navigator
                        totalPage={pageInfo.totalPageCount}
                        onClickPage={navigatePage}
                      />
                    </div>
                  </div>
                </div>
              </Parchment>
            </div>
          </div>
          <div className="col-span-1">
            <div
              style={{
                width: "510px",
                height: "870px",
              }}
            >
              <div className="h-1/5 flex justify-center items-center">
                {/* 임의의 값 입력 */}
                <MyInfo
                  width="450px"
                  height="160px"
                  tier={userRatingInfo.tier}
                  nickname={nickname}
                  reward={userRatingInfo.rating}
                  totalMatches={userRatingInfo.totalCount}
                  wins={userRatingInfo.winCount}
                  button={
                    <Button
                      width="103px"
                      height="35px"
                      label="내 정보"
                      background="var(--brown)"
                      color="white"
                      onClick={() => onClickMyPage()}
                    />
                  }
                />
              </div>
              <div className="h-2/5 flex justify-center items-center">
                <Parchment style={{ width: "450px", height: "320px" }}>
                  <div className="mt-5">
                    <Ranking rankers={rankers} />
                  </div>
                </Parchment>
              </div>
              <div className="h-2/5 flex justify-center items-center">
                <Parchment style={{ width: "450px", height: "320px", overflow:"auto" }}>
                  <div className="mt-1">
                    {/* 임의의 값 입력 */}
                    <ConnectList />
                  </div>
                </Parchment>
              </div>
            </div>
          </div>
        </div>
        {showRoomMakeModal && <RoomMakeModal close={closeModal} />}
        {isLoading && <Loading />}
      </WoodBackground>
    </>
  );
};

export default RoomsPage;
