package com.ruoyi.custom.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.custom.domain.AllCustContact;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.mapper.TLcCustContactMapper;
import com.ruoyi.custom.service.ITLcCustContactService;
import com.ruoyi.duncase.mapper.TLcDuncaseMapper;
import com.ruoyi.enums.ContactOriginEnum;
import com.ruoyi.enums.ContactRelaEnum;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 客户联系人信息Service业务层处理
 *
 * @author liurunkai
 * @date 2019-12-26
 */
@Service("com.ruoyi.custom.service.impl.TLcCustContactServiceImpl")
public class TLcCustContactServiceImpl implements ITLcCustContactService {
    @Autowired
    private TLcCustContactMapper tLcCustContactMapper;
    @Autowired
    private TLcDuncaseMapper tLcDuncaseMapper;

    /**
     * 查询客户联系人信息
     *
     * @param id 客户联系人信息ID
     * @return 客户联系人信息
     */
    @Override
    public TLcCustContact selectTLcCustContactById(Long id) {
        return tLcCustContactMapper.selectTLcCustContactById(id);
    }

    /**
     * 查询客户联系人信息列表
     *
     * @param tLcCustContact 客户联系人信息
     * @return 客户联系人信息
     */
    @Override
    public List<TLcCustContact> selectTLcCustContactList(TLcCustContact tLcCustContact) {
        return tLcCustContactMapper.selectTLcCustContactList(tLcCustContact);
    }

    /**
     * 新增客户联系人信息
     *
     * @param tLcCustContact 客户联系人信息
     * @return 结果
     */
    @Override
    public int insertTLcCustContact(TLcCustContact tLcCustContact) {
        tLcCustContact.setCreateBy(ShiroUtils.getUserId().toString());
        tLcCustContact.setModifyBy(ShiroUtils.getUserId());
        tLcCustContact.setOrigin(ContactOriginEnum.PAGE_ADD.getCode());
        tLcCustContact.setOrgName(this.tLcDuncaseMapper.selectOrgNameByOrgId(tLcCustContact.getOrgId()));
        return tLcCustContactMapper.insertTLcCustContact(tLcCustContact);
    }

    /**
     * 修改客户联系人信息
     *
     * @param tLcCustContact 客户联系人信息
     * @return 结果
     */
    @Override
    public int updateTLcCustContact(TLcCustContact tLcCustContact) {
        return tLcCustContactMapper.updateTLcCustContact(tLcCustContact);
    }

    /**
     * 删除客户联系人信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcCustContactByIds(String ids) {
        return tLcCustContactMapper.deleteTLcCustContactByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户联系人信息信息
     *
     * @param id 客户联系人信息ID
     * @return 结果
     */
    @Override
    public int deleteTLcCustContactById(Long id) {
        return tLcCustContactMapper.deleteTLcCustContactById(id);
    }

    @Override
    public List<TLcCustContact> findCustContactByCertificateNo(String certificateNo) {
        return this.tLcCustContactMapper.findCustContactByCertificateNo(certificateNo);
    }

    @Override
    public List<TLcCustContact> findCustContactByCaseNo(String caseNo) {
        return this.tLcCustContactMapper.findCustContactByCaseNo(caseNo);
    }

    @Override
    public List<TLcCustContact> findAllCustContactByCaseNo(String caseNo, String orgId, String importBatchNo) {
        List<TLcCustContact> contactList = this.tLcCustContactMapper.findAllCustContactByCaseNo(caseNo, orgId, importBatchNo);
//        List<AllCustContact> resultList = new ArrayList<>();
//        // 号码去重
//        List<String> phones = new ArrayList<>();
//        // 联系人去重
//        List<String> contacts = new ArrayList<>();
//        String tel = contactList.get(0).getTel();
////        System.out.println("tel="+tel);
////        System.out.println(StringUtils.isNotEmpty(contactList.get(0).getTel()));
////        System.out.println(StringUtils.isNotBlank(contactList.get(0).getTel()));
////        System.out.println(StringUtils.isNoneBlank(contactList.get(0).getTel()));
////        System.out.println(StringUtils.isNoneEmpty(contactList.get(0).getTel()));
//        for (TLcCustContact custContact : contactList) {
//            if (StringUtils.isNotBlank(custContact.getPhone()) && (!phones.contains(custContact.getPhone()) || !contacts.contains(custContact.getContactName()))) {
//                AllCustContact allCustContact = buildAllCustContact(custContact, custContact.getPhone());
//                resultList.add(allCustContact);
//                phones.add(custContact.getPhone());
//                contacts.add(custContact.getContactName());
//            }
//            if (StringUtils.isNotBlank(custContact.getTel()) && (!phones.contains(custContact.getTel()) || !contacts.contains(custContact.getContactName()))) {
//                AllCustContact allCustContact = buildAllCustContact(custContact,custContact.getTel());
//                resultList.add(allCustContact);
//                phones.add(custContact.getTel());
//                contacts.add(custContact.getContactName());
//            }
//            if (custContact.getRelation().equals(ContactRelaEnum.SELE.getCode()) && StringUtils.isNotBlank(custContact.getCompanyTel())) {
//                AllCustContact allCustContact = buildAllCustContact(custContact,custContact.getCompanyTel());
//                resultList.add(allCustContact);
//            }
//        }
        return contactList;
    }

    @Override
    public List<TLcCustContact> findAllHisDuncaseCustContactByCaseNo(String caseNo, String orgId, String importBatchNo) {
        List<TLcCustContact> contactList = this.tLcCustContactMapper.findAllHisDuncaseCustContactByCaseNo(caseNo, orgId, importBatchNo);
        return contactList;
    }

    @Override
    public int updateIsClose(Map<String, String> param) {
        return this.tLcCustContactMapper.updateIsClose(param);
    }

    @Override
    public List<Map<String, Object>> selectCustContactByTime(Date createTime, int pageNum, int pageSize) {
        return this.tLcCustContactMapper.selectCustContactByTime(createTime,pageNum,pageSize);
    }

    @Override
    public Integer selectCustContactCount(Date createTime) {
        return this.tLcCustContactMapper.selectCustContactCount(createTime);
    }

    private AllCustContact buildAllCustContact(TLcCustContact custContact, String phone) {
        AllCustContact allCustContact = AllCustContact.builder()
                .contactName(custContact.getContactName())
                .certificateNo(custContact.getCertificateNo())
                .relation(custContact.getRelation())
                .phone(phone)
                .id(custContact.getId())
                .isClose(custContact.getIsClose())
                .build();
        return allCustContact;
    }
}
