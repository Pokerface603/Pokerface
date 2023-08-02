const Button = ({
  width,
  height,
  background,
  fontSize,
  color,
  label,
  onClick,
  icon,
}) => {
  return (
    <>
      <button
        onClick={onClick}
        className="flex items-center justify-center gap-1 p-2 hover:brightness-90"
        style={{
          width,
          height,
          background,
          fontSize,
          fontFamily: "NexonGothic",
          fontWeight: "bold",
          color,
        }}
      >
          {icon}
          <span>{label}</span>
      </button>
    </>
  );
};

export default Button;