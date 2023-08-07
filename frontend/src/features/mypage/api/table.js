import blindTicket from "assets/images/ticket/bilnd-ticket.svg";
import normalTicket from "assets/images/ticket/normal-ticket.svg";
import emotionTicket from "assets/images/ticket/emotion-ticket.svg";

const getTicketImg = (mode) => {
  // eslint-disable-next-line default-case
  switch (mode) {
    case "blind":
      return blindTicket;
    case "normal":
      return normalTicket;
    case "emotion":
      return emotionTicket;
  }
};

const getRatingUpDown = (number) => {
  const n = parseInt(number);
  if (n < 0) {
    return <text className="text-center text-blue-700">↓{Math.abs(n)}</text>;
  } else {
    return <text className="text-center text-red-700">↑{Math.abs(n)}</text>;
  }
};

export { getTicketImg, getRatingUpDown };
