import { Navigate, Outlet } from "react-router-dom";
import Header from "../components/Header";
import Footer from "../components/Footer";

// import { useAuth } from "../hooks/useAuth";

const Layout = () => {

    // const { user } = useAuth();

    // if (user) {
    //     return <Navigate to="/my" />;
    // }

    return (
        <>
            <Header />
            <main className="mb-3">
                <Outlet />
            </main>
            <Footer />
        </>
    )
};

export default Layout;