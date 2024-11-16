import React from 'react';
import { Link, NavLink, useLocation } from 'react-router-dom';

interface NavbarProps {
    isAuth: boolean;
}

const Navbar: React.FC<NavbarProps> = ({ isAuth }) => {
    const location = useLocation();

    return (
        <header className="bg-gradient-to-r from-purple-600 to-indigo-600 text-white shadow-lg fixed top-0 left-0 w-full z-50 h-16">
            <nav className="container mx-auto px-6 py-4">
                <div className="flex justify-between items-center">
                    <Link to="/" className="text-2xl font-bold">
                        GearGrove
                    </Link>
                    <div className="flex space-x-6">
                        <NavLink
                            to="/"
                            className={({ isActive }) => (isActive ? "active" : "")}
                        >
                            Home
                        </NavLink>
                        <NavLink
                            to="/profile"
                            className={({ isActive }) => (isActive ? "active" : "")}
                        >
                            Profile
                        </NavLink>
                        <NavLink
                            to="/orders"
                            className={({ isActive }) => (isActive ? "active" : "")}
                        >
                            Orders
                        </NavLink>
                        <NavLink
                            to="/contact"
                            className={({ isActive }) => (isActive ? "active" : "")}
                        >
                            Contact
                        </NavLink>
                    </div>
                    {!isAuth && (
                        <div className="ml-6">
                            <Link
                                to="/auth"
                                className="bg-white text-purple-600 hover:bg-purple-600 hover:text-white transition duration-150 ease-in-out px-4 py-2 rounded-full shadow-lg"
                            >
                                Login or Register
                            </Link>
                        </div>
                    )}
                </div>
            </nav>
        </header>
    );
};

export default Navbar;
