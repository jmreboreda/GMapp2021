package gmoldes.persistence;


public class ClientManager {

    public ClientManager() {
    }

//    public List<PersonDTO> findAllFromNamePatternInAlphabeticalOrder(String namePattern) {
//
//        List<PersonDTO> personDTOList = new ArrayList<>();
//        PersonDAO personDAO = PersonDAO.PersonDAOFactory.getInstance();
//        List<PersonVO> personVOList = personDAO.findAllClientFromNamePatternInAlphabeticalOrder(namePattern);
//        for (PersonVO personVO : personVOList) {
//            PersonDTO personDTO = PersonDTO.create()
//                    .withId(personVO.getId())
//                    .withApellido1(personVO.getApellido1())
//                    .withApellido2(personVO.getApellido2())
//                    .withNombre(personVO.getNombre())
//                    .build();
//
//            personDTOList.add(personDTO);
//        }
//        return personDTOList;
//    }
//
//    public PersonDTO findPersonById(Integer id){
//
//        PersonDAO personDAO = PersonDAO.PersonDAOFactory.getInstance();
//        PersonVO personVO = personDAO.findPersonById(id);
//        Set<PhoneDTO> phoneDTOList = new HashSet<>();
//        for(PhoneVO phoneVO : personVO.getPhoneVOS()){
//            PhoneDTO phoneDTO = new PhoneDTO();
//            phoneDTO.setId(phoneVO.getId());
//            phoneDTO.setPhoneNumber(phoneVO.getPhoneNumber());
//            phoneDTOList.add(phoneDTO);
//        }
//
//        return PersonDTO.create()
//                .withApellido1(personVO.getApellido1())
//                .withApellido2(personVO.getApellido2())
//                .withNombre(personVO.getNombre())
//                .withPhones(phoneDTOList)
//                .build();
//    }
//
////    public Integer findPersonByStrictName(PersonDTO personDTO){
////
//////        PersonVO personVO = PersonMapper..proccesPersonDTOVO(personDTO);
//////        PersonDAO personDAO = PersonDAO.PersonDAOFactory.getInstance();
//////        Integer id = personDAO.findPersonByStrictName(personVO);
////
////        return id;
////    }
//
//    public Integer createPerson(PersonDTO personDTO){
//
//        PersonVO personVO = PersonMapper.mapToVO(personDTO);
//        PersonDAO personDAO = PersonDAO.PersonDAOFactory.getInstance();
//        Integer id = personDAO.createPerson(personVO);
//
//        return id;
//    }
//
//    public Integer findPhoneByPhoneNumber(String phoneNumber){
//
//        PersonDAO personDAO = PersonDAO.PersonDAOFactory.getInstance();
//        Integer phoneId = personDAO.findPhoneByPhoneNumber(phoneNumber);
//
//        return phoneId;
//    }
//
////    public Integer addNewPhoneToPerson(PersonDTO personDTO){
////
////        PersonVO personVO = mapper.proccesPersonDTOVO(personDTO);
////        PersonDAO personDAO = PersonDAO.PersonDAOFactory.getInstance();
////        PersonVO personVO1 = personDAO.addNewPhoneToPerson(personVO);
////
////        return personVO1.getId();
////
////
////    }
}
