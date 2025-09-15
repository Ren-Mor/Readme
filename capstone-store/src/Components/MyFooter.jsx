import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";

function MyFooter() {
  return (
    <footer className="d-flex bg-dark mt-2 fixed-bottom py-4">
      <div className="text-center text-white m-auto p-2 fs-5">
        <strong> HW STORE </strong>{" "}
        <div className="d-inline-block text-center text-nowrap fs-6">
          - Morelli Renato 2025 -
        </div>
        <div className="d-inline-block text-center text-nowrap ms-3 fs-6">
          La nostra mail: renato@unamail.it -
        </div>
        <div className="d-inline-block text-center text-nowrap ms-3 fs-6">
          Messaggiaci su Whatsapp: 3312324267
        </div>
      </div>
    </footer>
  );
}

export default MyFooter;
