package com.anton.gs.model.dao;

public class SqlQueries {
    public static final String SQL_FIND_USER_EMAIL = "select email from gs_user_basic_info where email = (?);";
    public static final String SQL_FIND_PASSWORD_BY_EMAIL = "select user_password from gs_user_basic_info where email = (?)";
    public static final String SQL_FIND_ID_BY_EMAIL = "select gs_user_basic_info_id from gs_user_basic_info where email = (?)";
    public static final String SQL_SELECT_FILE_NAME = "select file_name from gs_user_avatar gua where gs_user_email  = (?)";
    public static final String SQL_UPDATE_FILE_NAME = "update gs_user_avatar set file_name = (?) where gs_user_email  = (?)";
    public static final String SQL_INSERT_EMAIL_INTO_AVATAR_TABLE = "insert into gs_user_avatar (gs_user_email) values (?)";
    public static final String SQL_FIND_USER_ROLE_BY_ID = "select user_role from gs_user left join gs_u_pi gup \n" +
            "on\n" +
            "gs_user.gs_u_pi_id  = gup.gs_u_pi_id \n" +
            "left join gs_user_role gur \n" +
            "on gup.user_role_id  = gur.gs_user_role_id \n" +
            "where gs_user.gs_user_id  = (?)";

    public static final String SQL_FIND_ALL_USERS = "select gs_user_id, email, user_password, nickname, user_role , tutor_degree, skill_level, is_active   from gs_user gu\n" +
            " join gs_user_basic_info gubi\n" +
            " on gu.gs_user_basic_info_id = gubi.gs_user_basic_info_id \n" +
            " left join gs_u_pi gupi\n" +
            " on gu.gs_u_pi_id = gupi.gs_u_pi_id\n" +
            "left join gs_user_role gur\n" +
            " on gupi.user_role_id = gur.gs_user_role_id\n" +
            " left join gs_tutor_degree_level gtdl \n" +
            " on gupi.tutor_degree_level_id = gtdl.gs_tutor_degree_level_id\n" +
            "left join gs_student_skill_level gssl \n" +
            "on gupi.student_skill_level_id = gssl.gs_student_skill_level_id";

    public static final String SQL_ADD_USER = "with basic_info_insert as (\n" +
            "\t\tinsert into gs_user_basic_info (email, user_password, nickname)\n" +
            "\t\tvalues ( (?), (?), (?) )\n" +
            "\t\treturning gs_user_basic_info_id as b_id\n" +
            "),\n" +
            "\n" +
            "professional_info_insert as (\n" +
            "\tinsert into gs_u_pi (user_role_id, tutor_degree_level_id, student_skill_level_id)\n" +
            "\tvalues ((?), (?) , (?))\n" +
            "\treturning gs_u_pi_id as p_id\n" +
            ")\n" +
            "\n" +
            "insert into gs_user (gs_user_basic_info_id, gs_u_pi_id)\n" +
            "values (\n" +
            "(select b_id from basic_info_insert), (select p_id from professional_info_insert)\n" +
            ")";

    public static final String SQL_FIND_USER_BY_EMAIL = "" +
            "select gs_user_id, email, user_password, nickname, user_role , tutor_degree, skill_level, is_active   from gs_user gu\n" +
            " join gs_user_basic_info gubi\n" +
            " on gu.gs_user_basic_info_id = gubi.gs_user_basic_info_id \n" +
            " left join gs_u_pi gupi\n" +
            " on gu.gs_u_pi_id = gupi.gs_u_pi_id\n" +
            "left join gs_user_role gur\n" +
            " on gupi.user_role_id = gur.gs_user_role_id\n" +
            " left join gs_tutor_degree_level gtdl \n" +
            " on gupi.tutor_degree_level_id = gtdl.gs_tutor_degree_level_id\n" +
            "left join gs_student_skill_level gssl \n" +
            "on gupi.student_skill_level_id = gssl.gs_student_skill_level_id\n" +
            "where email = (?)";

    public static final String SQL_ACTIVATE_USER = "update gs_user_basic_info set is_active = 'true'" +
            " where email = (?)";

    public static final String SQL_IS_USER_ACTIVE = "select is_active from gs_user_basic_info " +
            "gubi where email = (?)";

    public static final String SQL_SELECT_USER_ID = "select gs_user_basic_info_id as id  from gs_user_basic_info  where email = (?)";
    public static final String SQL_UPDATE_USER = "update gs_u_pi set user_role_id = (?), student_skill_level_id = (?), tutor_degree_level_id = (?) where gs_u_pi_id = (?)";
    public static final String SQL_BAN_USER = "insert into gs_blocked_user (email) values (?)";
    public static final String SQL_UPDATE_STUDENT = "update gs_u_pi set  student_skill_level_id = (?)  where gs_u_pi_id = (?)";

    public static final String SQL_UNBAN_USER = "delete from gs_blocked_user  where email = (?)";

    public static final String SQL_SELECT_BANNED_USER = "select email from gs_blocked_user";
    public static final String SQL_SELECT_GENRE_ID = "select gs_genre_id from gs_genres where genre = (?)";
    public static final String SQL_SELECT_GUITAR_ID = "select gs_guitar_type_id from gs_guitar_types ggt where guitar_type = (?)";
    public static final String SQL_SELECT_SKILL_LEVEL_ID = "select gs_student_skill_level_id from gs_student_skill_level where skill_level = (?)";
    public static final String SQL_IS_USER_BANNED = "select email from gs_blocked_user where email  = (?)";
    public static final String SQL_SELECT_REST = "select rest from gs_bank_account gba where bank_account_number = (?)";
    public static final String SQL_SELECT_BANK_CLIENT_EMAIL = "select email from gs_bank_account gba where bank_account_number = (?)";
    public static final String SQL_UPDATE_REST = "update gs_bank_account set rest = rest - (?) where bank_account_number = (?)";
    public static final String SQL_INSERT_ORDER = "insert into gs_order (email, genre_name, is_paid) values ((?), (?), true)";
    public static final String SQL_SELECT_ORDERS = "select gs_order_id, email, genre_name, is_paid, is_confirmed from gs_order order by is_confirmed desc";
    public static final String SQL_UPDATE_ORDER = "update gs_order set is_confirmed = true where gs_order_id = (?)";
    public static final String SQL_INSERT_CONTRACT = "with gs_condition_insert as\n" +
            "(insert into gs_contract_condition (start_date , finish_date , guitar_type_id, genre_id, student_current_level_id, student_target_level_id)\n" +
            "values ((?),(?),(?),(?),(?),(?)) \n" +
            "returning gs_contract_condition_id as condition_id)\n" +
            "\n" +
            "insert into gs_contract (contract_condition_id, tutor_id, student_id) values ((select condition_id from gs_condition_insert), (?),(?));";


    public static final String SQL_FIND_USERS_WITH_ROLE = "select  email, user_role from gs_user gu\n" +
            " join gs_user_basic_info gubi\n" +
            " on gu.gs_user_basic_info_id = gubi.gs_user_basic_info_id \n" +
            " left join gs_u_pi gupi\n" +
            " on gu.gs_u_pi_id = gupi.gs_u_pi_id\n" +
            "left join gs_user_role gur\n" +
            " on gupi.user_role_id = gur.gs_user_role_id\n" +
            " left join gs_tutor_degree_level gtdl \n" +
            " on gupi.tutor_degree_level_id = gtdl.gs_tutor_degree_level_id\n" +
            "left join gs_student_skill_level gssl \n" +
            "on gupi.student_skill_level_id = gssl.gs_student_skill_level_id where user_role = (?)";

    public static final String SQL_SELECT_CONTRACTS = "select\n" +
            "gs_contract_id as c_id,\n" +
            "email as tutor,\n" +
            "(select email from gs_user_basic_info gubi where gubi.gs_user_basic_info_id = (select student_id from gs_contract gc where gcc.gs_contract_condition_id = gc.contract_condition_id)) as student,\n" +
            "( select skill_level from gs_u_pi gup \n" +
            "join gs_student_skill_level gssl on gup.student_skill_level_id = gssl.gs_student_skill_level_id \n" +
            "join gs_user gu on gup.gs_u_pi_id = gu.gs_u_pi_id \n" +
            "join gs_user_basic_info gubi on gu.gs_user_basic_info_id = gubi.gs_user_basic_info_id where email =\n" +
            "(select email from gs_user_basic_info gubi where gubi.gs_user_basic_info_id = (select student_id from gs_contract gc where gcc.gs_contract_condition_id = gc.contract_condition_id))) as current,\n" +
            "\n" +
            "(select skill_level from gs_student_skill_level gssl2 where gs_student_skill_level_id = gcc.student_target_level_id ) as target,\n" +
            "guitar_type as guitar, \n" +
            "genre,\n" +
            "start_date as start,\n" +
            "finish_date as finish\n" +
            "from gs_contract_condition gcc \n" +
            "join gs_student_skill_level gssl on gcc.student_current_level_id = gssl.gs_student_skill_level_id \n" +
            "join gs_guitar_types ggt on gcc.guitar_type_id = ggt.gs_guitar_type_id\n" +
            "join gs_genres gg on gcc.genre_id = gg.gs_genre_id\n" +
            "join gs_contract gc on gcc.gs_contract_condition_id = gc.contract_condition_id\n" +
            "join gs_user gu on gc.tutor_id = gu.gs_user_id \n" +
            "join gs_user_basic_info gubi on gu.gs_user_basic_info_id = gubi.gs_user_basic_info_id\n" +
            "join gs_u_pi gup on gu.gs_u_pi_id = gup.gs_u_pi_id \n" +
            "join gs_user_role gur on gup.user_role_id = gur.gs_user_role_id\n" +
            "";


    public static final String SQL_SELECT_COURSES = SQL_SELECT_CONTRACTS +
            "where (select email from gs_user_basic_info gubi where gubi.gs_user_basic_info_id = (select student_id from gs_contract gc where gcc.gs_contract_condition_id = gc.contract_condition_id)) = (?)\n" +
            ";";

    public static final String SQL_SELECT_STUDENTS = SQL_SELECT_CONTRACTS +
            "where email = (?)";

    private SqlQueries() {
    }
}
